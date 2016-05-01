package hu.prf.messaging.dao;

import hu.prf.messaging.entity.Message;
import hu.prf.messaging.entity.UserActivityModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Named
public class MessageDAO extends GenericDAO<Message, Long> {

    private static final long serialVersionUID = -5899328697322490825L;

    public MessageDAO() {
        super(Message.class);
    }

    public List<Message> getTwoUsersMessages(long userId1, long userId2) {
        TypedQuery<Message> q = getEntityManager()
                .createQuery(
                        "select m "
                                + "from Message m "
                                + "where m.sender.id = :uid1 and m.reciever.id = :uid2 or "
                                + "m.sender.id = :uid2 and m.reciever.id = :uid1 "
                                + "order by m.date", Message.class);
        q.setParameter("uid1", userId1);
        q.setParameter("uid2", userId2);
        return q.getResultList();
    }

    public void setMessagesToSeen(long loggedInUid, long otherUid) {
        Query q = getEntityManager()
                .createQuery(
                        "update Message "
                                + "set seen = true "
                                + "where sender.id = :other_uid and reciever.id = :logged_in_uid");
        q.setParameter("logged_in_uid", loggedInUid);
        q.setParameter("other_uid", otherUid);
        q.executeUpdate();
    }

    public List<UserActivityModel> getNumOfMessagesGrouppedBySenders(
            long loggedInUid) {
        System.out.println("Called with uid: " + loggedInUid);

        Query q = getEntityManager().createQuery(
                "select m.sender.name as name, count(m.id) as numOfMsgs "
                        + "from Message m join m.reciever s "
                        + "where s.id = :uid "
                        + "group by s.name order by numOfMsgs desc ");
        q.setParameter("uid", loggedInUid);
        return mapResultsToActivityModel(q);
    }

    public List<UserActivityModel> getMostActiveSenders() {
        Query q = getEntityManager().createQuery(
                "select s.name as name, count(m.id) as numOfMsgs "
                        + "from Message m join m.sender s "
                        + "group by m.sender " + "order by numOfMsgs desc");
        q.setMaxResults(30);
        return mapResultsToActivityModel(q);
    }

    private List<UserActivityModel> mapResultsToActivityModel(Query q) {
        List<UserActivityModel> list = new ArrayList<UserActivityModel>();
        List<Object[]> results = q.getResultList();
        results.stream().forEach((record) -> {
            String name = (String) record[0];
            Long numOfMsgs = (Long) record[1];
            UserActivityModel model = new UserActivityModel(name, numOfMsgs);
            list.add(model);
        });
        return list;
    }

    public List<UserActivityModel> getMostActiveChats() {
         Query q = getEntityManager().createQuery(
                 "select m.sender.name as sender, m.reciever.name as receiver, count(m.id) as msgs " +
                         "from Message m " +
                         "group by m.sender, m.reciever"
         );
         List<UserActivityModel> list = new ArrayList<UserActivityModel>();
         List<Object[]> results = q.getResultList();
         results.stream().forEach((record) -> {
             String name = (String) record[0];
             String otherName = (String) record[1];
             Long numOfMsgs = (Long) record[2];
             UserActivityModel model = new UserActivityModel(name, numOfMsgs);
             model.setOtherName(otherName);
             
             for(UserActivityModel elem : list) {
                 if(elem != null && elem.getName().equals(otherName)
                         && elem.getOtherName().equals(name)) {
                     elem.setNumberOfMessages(elem.getnumOfMsgs() + numOfMsgs);
                     return;
                 }
             }
             list.add(model);
         });
         
         list.sort((o1, o2) -> o1.getnumOfMsgs().compareTo(o2.getnumOfMsgs()));
         return list;
     }
}
