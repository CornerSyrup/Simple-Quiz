package jp.ac.hal.kadai07_ih13b_11.model;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represent a XML parser for quiz content.
 */
public class QuizContentParser {
    /**
     * Parses quiz content with provided parser.
     *
     * @param parser XML parser which sources the quiz xml.
     * @return List of questions.
     * @throws IOException            Thrown when fail to load quiz xml content.
     * @throws XmlPullParserException Thrown when the parser's content is invalid.
     */
    public List<Question> Parse(XmlResourceParser parser) throws IOException, XmlPullParserException {
        Stack<String> tagTrace = new Stack<>();

        int ev = parser.next();
        if (ev != XmlResourceParser.START_DOCUMENT) {
            throw new XmlPullParserException("fail to parse document");
        }
        ev = parser.next();

        Question que = null;
        String ans_cont = "";
        boolean ans_corr = false;
        List<Question> ret = new ArrayList<>();

        while (ev != XmlResourceParser.END_DOCUMENT) {
            switch (ev) {
                case XmlResourceParser.START_TAG:
                    tagTrace.push(parser.getName());

                    switch (tagTrace.peek()) {
                        case "answer":
                            ans_corr = Boolean.parseBoolean(parser.getAttributeValue(null, "correct"));
                            break;
                    }
                    break;
                case XmlPullParser.TEXT:
                    switch (tagTrace.peek()) {
                        case "question":
                            que = new Question(parser.getText());
                            break;
                        case "answer":
                            ans_cont = parser.getText();
                            break;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    switch (tagTrace.peek()) {
                        case "answer":
                            que.addAnswer(new Answer(ans_cont, ans_corr));
                            ans_cont = "";
                            ans_corr = false;
                            break;
                        case "question":
                            ret.add(que);
                            que = null;
                            break;
                    }

                    tagTrace.pop();
                    break;
            }

            ev = parser.next();
        }

        return ret;
    }
}
