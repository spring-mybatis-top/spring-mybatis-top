//本代码由 http://spring-mybatis.top 生成
package spring_mybatis.top.sample.h2disk.domain.enumeration;

/**
 * The Language enumeration.
 */
public enum Language {
    FRENCH("法语"),
    ENGLISH("英语"),
    Chinese("汉语");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
