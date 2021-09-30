package jp.ac.hal.kadai07_ih13b_11.model;

import android.app.Activity;
import android.content.res.XmlResourceParser;

import androidx.annotation.XmlRes;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class QuizContentParser {
    public List<Question> Parse(Activity activity, @XmlRes int resId) throws IOException, XmlPullParserException {
        XmlResourceParser parser = activity.getResources().getXml(resId);
        Stack<String> tagTrace = new Stack<>();

        int ev = parser.next();
        if (ev != XmlResourceParser.START_DOCUMENT) {
            throw new XmlPullParserException("fail to parse document");
        }
        ev = parser.next();

        Question que = null;
        Answer ans = null;
        List<Question> ret = new ArrayList<>();

        while (ev != XmlResourceParser.END_DOCUMENT) {
            switch (ev) {
                case XmlResourceParser.START_TAG:
                    tagTrace.push(parser.getName());

                    switch (tagTrace.peek()) {
                        case "question":
                            que = new Question();
                            break;
                        case "answer":
                            ans = new Answer().setCorrect(Boolean.parseBoolean(parser.getAttributeValue(null, "correct")));
                            break;
                    }
                    break;
                case XmlPullParser.TEXT:
                    switch (tagTrace.peek()) {
                        case "question":
                            que.setQuestion(parser.getText());
                            break;
                        case "answer":
                            ans.setContent(parser.getText());
                            break;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    switch (tagTrace.peek()) {
                        case "answer":
                            que.addAnswer(ans);
                            ans = null;
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
