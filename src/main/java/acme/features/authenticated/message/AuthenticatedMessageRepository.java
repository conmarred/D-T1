
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.entities.spams.Spam;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findMany(int id);

	@Query("select m from Message m where m.id = ?1")
	Message findOne(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findUserAccount(int id);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findMessageThread(int id);

	@Query("select s from Spam s")
	Spam findSpam();

	@Query("select count(mt) > 0 from MessageThread mt join mt.users u where u.id = ?1 and mt.id = ?2")
	boolean isCorrectMessageThread(int idAccount, int idMessageThread);

	@Query("select count(m) > 0 from Message m where m.id = ?2 and m.messageThread.id in(select mt.id from MessageThread mt join mt.users u where u.id = ?1)")
	boolean isCorrectMessage(int idAccount, int idMessage);
}
