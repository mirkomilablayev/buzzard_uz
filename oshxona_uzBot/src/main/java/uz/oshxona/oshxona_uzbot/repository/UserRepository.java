package uz.oshxona.oshxona_uzbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.oshxona.oshxona_uzbot.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByChatId(String chatId);

    @Query(nativeQuery = true, value = "select count(*) from users st where st.id not in (select btc.student_id from booking_to_course btc)")
    int getNonFollowersCount();
}
