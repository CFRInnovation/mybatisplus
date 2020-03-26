# mybatisplus
 
自定义处理bean生命周期的初始化和销毁的方法：
1. @Bean 中指定init-method和destory-method
2. bean类实现两个接口：InitializingBean（定义初始化逻辑）和DisposableBean(定义销毁逻辑)
3. 使用JSR250 注解@PostConstruct 在自定义的初始化方法上，使用注解@PreDistory在自定义的销毁方法上
4. 实现接口BeanPostProcessor, 并实现两个方法postProcessBeforeInitialization （在初始化之前工作）
	和postProcessAfterInitialization （在初始化之后调用）
	
@Value使用：
1. 基本数值
2. 使用SpEL：#{}
3. 使用${},取出配置文件里的值（以及运行环境变量里的值）