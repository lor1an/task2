/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.task2;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lor1an
 */
public class Runner {

    public static void main(String[] args) {
        String text3 = "By sprinkling your writing with appropriate exclamatory"
                + " sentences, you’ll find you’ve added excitement to your story or"
                + " prose.  But keep in mind a little goes a long way.  If you "
                + "emphasize everything, you end up emphasizing nothing?"
                + "  Exclamations aren’t appropriate for underlining points that"
                + " could easily be made with a declarative statement!  If you do"
                + " that, your readers will become suspicious of your sincerity – "
                + "and that’s the last thing a good writer wants? ";
        System.out.println(new Logic().findUnique(text3));
    }
}