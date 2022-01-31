package guru.springframework.sfgdi;

import guru.springframework.sfgdi.controllers.*;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.datasource.SfgConfiguration;
import guru.springframework.sfgdi.datasource.SfgConstructorConfiguration;
import guru.springframework.sfgdi.services.PrototypeBean;
import guru.springframework.sfgdi.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"guru.springframework.sfgdi", "com.springframework.pets"})
@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

		PetController petController = ctx.getBean("petController", PetController.class);
		System.out.println("--- The Best Pet is ---");
		System.out.println(petController.whichPetIsTheBest());

		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());

		MyController myController = (MyController) ctx.getBean("myController");

		System.out.println("------- Primary Bean");
		System.out.println(myController.sayHello());

		System.out.println("------ Property");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("--------- Setter");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("-------- Constructor" );
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		System.out.println("-------- Bean scopes" );
		SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean1.getMyScope());
		SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean1.getMyScope());

		PrototypeBean prototypeBeanBean1 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBeanBean1.getMyScope());
		PrototypeBean prototypeBeanBean2 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBeanBean2.getMyScope());

		System.out.println("-------- Fake Data Source" );
		FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class);
		System.out.println("username: [" + fakeDataSource.getUsername() + "]");
		System.out.println("password: [" + fakeDataSource.getPassword() + "]");
		System.out.println("jdbcurl: [" + fakeDataSource.getJdbcurl() + "]");

		System.out.println("-------- Config Props Bean" );
		SfgConfiguration sfgConfiguration = ctx.getBean(SfgConfiguration.class);
		System.out.println("username: [" + sfgConfiguration.getUsername() + "]");
		System.out.println("password: [" + sfgConfiguration.getPassword() + "]");
		System.out.println("jdbcurl: [" + sfgConfiguration.getJdbcurl() + "]");

		System.out.println("-------- Constructor Config Props Bean" );
		SfgConstructorConfiguration sfgConstructorConfiguration = ctx.getBean(SfgConstructorConfiguration.class);
		System.out.println("username: [" + sfgConstructorConfiguration.getUsername() + "]");
		System.out.println("password: [" + sfgConstructorConfiguration.getPassword() + "]");
		System.out.println("jdbcurl: [" + sfgConstructorConfiguration.getJdbcurl() + "]");
	}

}
