package it.nicolasanti.autoconfigure;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Italian is a great language but there is not a general rule
 * to pluralize name: this annotation let you specifiy your
 * entity class plural manually.
 * 
 * 
 * @author nicola
 *
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
public @interface Plural {
	public String value();
}
