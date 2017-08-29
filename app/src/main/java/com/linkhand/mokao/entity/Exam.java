package com.linkhand.mokao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JCY on 2017/8/22.
 * 说明：考试题及其考试题选项
 */

public class Exam implements Serializable {
    private String question; //题目
    private List<Answer> answers;// 问题

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public static class Answer implements Serializable {
        private String answers;
        private boolean flag;

        public Answer(String answers) {
            this.answers = answers;
        }

        public String getAnswers() {
            return answers;
        }

        public void setAnswers(String answers) {
            this.answers = answers;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}
