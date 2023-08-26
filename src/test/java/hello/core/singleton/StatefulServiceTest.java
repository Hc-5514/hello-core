package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);

		// Thread A: A 사용자 10,000원 주문
		statefulService1.order("userA", 10000);
		// Thread B: B 사용자 20,000원 주문
		statefulService2.order("userB", 20000);

		// Thread A: 사용자 A 의 주문 금액 조회
		int price = statefulService1.getPrice();
		System.out.println("price: " + price); // 20,000원이 나오는 문제 발생!!

		Assertions.assertThat(statefulService1.getPrice()).isEqualTo(statefulService2.getPrice());
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}