
package acme.features.authenticated.messageThreads;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.spams.Spam;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select mt from MessageThread mt join mt.users u where u.id = ?1")
	Collection<MessageThread> findMany(int id);

	@Query("select count(mt) > 0 from MessageThread mt join mt.users u where mt.id = ?1 and u.id = ?2")
	boolean isCorrectMT(int idMT, int idU);

	@Query("select ua from UserAccount ua where ua.username = ?1")
	UserAccount findUserAccountByUsername(String username);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findUserAccountById(int id);

	@Query("select u from MessageThread mt join mt.users u where mt.id = ?1")
	Collection<UserAccount> findUserOfMessageThread(int id);

	@Query("select u.id from MessageThread mt join mt.users u where mt.id = ?1")
	List<Integer> isCreatorUser(int id);

	@Query("select s from Spam s")
	Spam findSpam();

}
