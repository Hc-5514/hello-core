package hello.core.member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member> store = new ConcurrentHashMap<>(); // 동시성 이슈 때문에 일반 HashMap 이 아닌, ConcurrentHashMap 을 사용해야 한다.

	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long memberId) {
		return store.get(memberId);
	}
}
