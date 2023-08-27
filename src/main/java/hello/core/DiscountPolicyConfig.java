package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

@Configuration
public class DiscountPolicyConfig {
	
	@Bean
	public DiscountPolicy rateDiscountPolicy() {
		return new RateDiscountPolicy();
	}

	@Bean
	public DiscountPolicy fixDiscountPolicy() {
		return new FixDiscountPolicy();
	}
}

