//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample_mysql;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.belongToAnyOf;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packagesOf = SampleProjectMysqlApp.class, importOptions = DoNotIncludeTests.class)
class TechnicalStructureTest {

    // prettier-ignore
    @ArchTest
    static final ArchRule respectsTechnicalArchitectureLayers = layeredArchitecture()        .layer("Config").definedBy("..config..").layer("Web").definedBy("..web..")        .optionalLayer("Service").definedBy("..service..")        .layer("Security").definedBy("..security..").layer("Persistence").definedBy("..repository..")        .layer("Domain").definedBy("..domain..")
        .whereLayer("Config").mayNotBeAccessedByAnyLayer().whereLayer("Web").mayOnlyBeAccessedByLayers("Config")        .whereLayer("Service").mayOnlyBeAccessedByLayers("Web", "Config")        .whereLayer("Security").mayOnlyBeAccessedByLayers("Config", "Service", "Web").whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service", "Security", "Web", "Config")        .whereLayer("Domain").mayOnlyBeAccessedByLayers("Persistence", "Service", "Security", "Web", "Config")
        .ignoreDependency(belongToAnyOf(SampleProjectMysqlApp.class), alwaysTrue())        .ignoreDependency(alwaysTrue(), belongToAnyOf(spring_mybatis.top.sample_mysql.config.Constants.class,spring_mybatis.top.sample_mysql.config.ApplicationProperties.class        ));
}
