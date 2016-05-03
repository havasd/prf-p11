package hu.prf.messaging.controller;

import hu.prf.messaging.dao.MessageDAO;
import hu.prf.messaging.dao.UserDAO;
import hu.prf.messaging.entity.UserActivityModel;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

@Named
@ViewScoped
public class StatisticsController implements Serializable {

    private static final long serialVersionUID = 3346759248048030119L;

    @Inject
    private MessageDAO messageDAO;
    
    @Inject
    private UserDAO userDAO;
    
    private BarChartModel mostActiveUsersBarChartModel;
    
    private PieChartModel numOfMsgsGrouppedBySenderPieChartModel;
    
    public PieChartModel getNumOfMsgsGrouppedBySenderPieChartModel() {
        return numOfMsgsGrouppedBySenderPieChartModel;
    }

    private List<UserActivityModel> mostActiveSenders;
    
    private List<UserActivityModel> sendersToUser;
    
    private List<UserActivityModel> mostActiveChats;

    @PostConstruct
    public void init() {
        mostActiveSenders = messageDAO.getMostActiveSenders();
        createMostActiveUsersBarChartModel();
        sendersToUser = messageDAO.getNumOfMessagesGrouppedBySenders(userDAO.getLoggedInUser().getId());
        createSendersToUserPieChartModel();
        mostActiveChats = messageDAO.getMostActiveChats();
    }
    
    private void createSendersToUserPieChartModel() {
        numOfMsgsGrouppedBySenderPieChartModel = new PieChartModel();
        
        for (UserActivityModel elem : getSendersToUser()) {
            numOfMsgsGrouppedBySenderPieChartModel.set(elem.getName(), elem.getnumOfMsgs());
        }
         
        numOfMsgsGrouppedBySenderPieChartModel.setTitle("Küldők megoszlása");
        numOfMsgsGrouppedBySenderPieChartModel.setLegendPosition("w");
    }
    
    private void createMostActiveUsersBarChartModel() {
        mostActiveUsersBarChartModel = new BarChartModel();
 
        ChartSeries userDataSeries = new ChartSeries();
        for (UserActivityModel elem : getMostActiveSenders()) {
            userDataSeries.set(elem.getName(), elem.getnumOfMsgs());
        }
        mostActiveUsersBarChartModel.addSeries(userDataSeries);
        
        mostActiveUsersBarChartModel.setTitle("Legaktívabb felhasználók");
        mostActiveUsersBarChartModel.setAnimate(true);
         
        Axis xAxis = mostActiveUsersBarChartModel.getAxis(AxisType.X);
        xAxis.setLabel("Felhasználók");
         
        Axis yAxis = mostActiveUsersBarChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Üzenetek száma");
        if (!mostActiveSenders.isEmpty()) {
            yAxis.setMax(mostActiveSenders.get(0).getnumOfMsgs() + 10);
        }
    }
    
    public BarChartModel getMostActiveUsersBarChartModel() {
        return mostActiveUsersBarChartModel;
    }
    
    public List<UserActivityModel> getMostActiveSenders() {
        return mostActiveSenders;
    }
    
    public List<UserActivityModel> getSendersToUser() {
        return sendersToUser;
    }
    
    public List<UserActivityModel> getMostActiveChats() {
        return mostActiveChats;
    }

    public void setMostActiveChat(List<UserActivityModel> mostActiveChat) {
        this.mostActiveChats = mostActiveChat;
    }


}
