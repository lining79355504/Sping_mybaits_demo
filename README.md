# Sping_mybaits_demo



java -jar src/jdbcdriver/mybatis-generator-core-1.3.6.jar -configfile src/main/resources/generatorConfig.xml -overwrite

java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml
java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml -overwrite
java -cp mybatis-generator-core-x.x.x.jar org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml
java -cp mybatis-generator-core-x.x.x.jar org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml -overwrite



mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate 