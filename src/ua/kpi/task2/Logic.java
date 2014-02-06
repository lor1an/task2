/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.task2;

import java.util.HashSet;
/**
 *
 * @author lor1an
 */
public class Logic {

    public static final String SENTENCE_REGEXP_1 = "\\.";
    public static final String SENTENCE_REGEXP_2 = "\\?";
    public static final String SENTENCE_REGEXP_3 = "\\!";
    public static final String WORD_REGEXP = " ";
    public String[] sentences;
    public String[][] sentenceWords;

    //Разбиение входящего текста на предложения
    public void makeSentences(String text) {
        sentences = text.split(SENTENCE_REGEXP_1);
        int countInter = 0, countExlc = 0, intrgSentences, exlclamSentences;
        for (int i = 0; i < sentences.length; i++) {
            //Подсчет вопросительных предложений:
            intrgSentences = sentences[i].split(SENTENCE_REGEXP_2).length;
            if (intrgSentences > 1) {
                countInter += intrgSentences;
            }
            //Подсчет восклицательных предложений:
            exlclamSentences = sentences[i].split(SENTENCE_REGEXP_3).length;
            if (exlclamSentences > 1) {
                countExlc += exlclamSentences;
            }
        }
        //Повторное разбиение предложений, уже с учетом вопросительных и 
        //восклицательных предложений:
        if (countInter > 0 || countExlc > 0) {
            int count = countInter + countExlc;
            if (countInter > 0 && countExlc > 0) {
                count -= 2;
            } else {
                count -= 1;
            }
            String[] temp = new String[sentences.length + count];
            for (int i = 0, k = 0; i < sentences.length; i++) {
                if (sentences[i].contains("!") && sentences[i].contains("?")) {
                    String[] temp2 = sentences[i].split(SENTENCE_REGEXP_2);
                    for (int j = 0; j < temp2.length; j++) {
                        if (temp2[j].contains("!")) {
                            String[] temp3 = temp2[j].split(SENTENCE_REGEXP_3);
                            for (int l = 0; l < temp3.length; l++) {
                                temp[k] = temp3[l];
                                k++;
                            }
                        } else {
                            temp[k] = temp2[j];
                            k++;
                        }
                    }
                } else if (sentences[i].contains("?")) {
                    String[] temp2 = sentences[i].split(SENTENCE_REGEXP_2);
                    for (int j = 0; j < temp2.length; j++) {
                        temp[k] = temp2[j];
                        k++;
                    }
                } else if (sentences[i].contains("!")) {
                    String[] temp2 = sentences[i].split(SENTENCE_REGEXP_3);
                    for (int j = 0; j < temp2.length; j++) {
                        temp[k] = temp2[j];
                        k++;
                    }
                } else {
                    temp[k] = sentences[i];
                    k++;
                }
            }
            sentences = temp;
        }
    }

    //Разбиение предложений на слова, при этом не учитывается знаки препинания
    public void makeWords(String[] sentences) {
        sentenceWords = new String[sentences.length][];
        for (int i = 0; i < sentences.length; i++) {
            sentenceWords[i] = sentences[i].split(WORD_REGEXP);
        }
    }

    //Нахождение уникальных слов с первого предложения
    public HashSet<String> findUnique(String text) {
        HashSet<String> uniqueWords = new HashSet<>();
        makeSentences(text);
        makeWords(sentences);
        for (int i = 0; i < sentenceWords[0].length; i++) {
            boolean flag = true;
            for (int j = 1; j < sentenceWords.length; j++) {
                if (!flag) {
                    break;
                }
                for (int k = 0; k < sentenceWords[j].length; k++) {
                    //Учет возможных знаков препинания при сравнении слов первого
                    //предложения с остальными:
                    if (!sentenceWords[0][i].matches("[a-zA-z]+\\W")) {
                        if (sentenceWords[0][i].equalsIgnoreCase(sentenceWords[j][k])) {
                            flag = false;
                            break;
                        }
                    } else {
                        String temp = sentenceWords[0][i].substring(0, sentenceWords[0][i].length() - 1);
                        if (temp.equalsIgnoreCase(sentenceWords[j][k])) {
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if (flag) {
                if (!sentenceWords[0][i].matches("[a-zA-z]+\\W")) {
                    uniqueWords.add(sentenceWords[0][i]);
                } else {
                    uniqueWords.add(sentenceWords[0][i].substring(0, sentenceWords[0][i].length() - 1));
                }
            }
        }
        return uniqueWords;

    }
}
