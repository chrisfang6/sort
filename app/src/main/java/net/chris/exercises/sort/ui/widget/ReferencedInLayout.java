package net.chris.exercises.sort.ui.widget;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Informs that this class is referenced in xml layout and this usage may not show while searching for usages
 */
@Retention(CLASS)
public @interface ReferencedInLayout {
}
