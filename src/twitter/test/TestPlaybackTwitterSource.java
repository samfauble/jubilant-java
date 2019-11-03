package twitter.test;

import org.junit.Test;

import static java.awt.Color.gray;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import query.Query;
import twitter.PlaybackTwitterSource;
import twitter.TwitterSource;
import twitter4j.Status;
import ui.Application;
import util.ObjectSource;

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;


/**
 * Test the basic functionality of the TwitterSource
 */
public class TestPlaybackTwitterSource {

    private ObjectSource source = new ObjectSource("data/TwitterCapture.jobj");

    @Test
    public void testSetup() {
        PlaybackTwitterSource source = new PlaybackTwitterSource(1.0);
        TestObserver to = new TestObserver();
        source.addObserver(to);
        source.setFilterTerms(set("food"));
        pause(3 * 1000);
        assertTrue("Expected getNTweets() to be > 0, was " + to.getNTweets(), to.getNTweets() > 0);
        assertTrue( "Expected getNTweets() to be <= 10, was " + to.getNTweets(),to.getNTweets() <= 10);
        int firstBunch = to.getNTweets();
        System.out.println("Now adding 'the'");
        source.setFilterTerms(set("food", "the"));
        pause(3 * 1000);
        assertTrue( "Expected getNTweets() to be > 0, was " + to.getNTweets(),to.getNTweets() > 0);
        assertTrue( "Expected getNTweets() to be < firstBunch (" + firstBunch + "), was " + to.getNTweets(),to.getNTweets() > firstBunch);
        assertTrue("Expected getNTweets() to be <= 10, was " + to.getNTweets(),to.getNTweets() <= 10);
    }

@Test
public void testAddQuery(){
        JMapViewer jmv;
       Query query = new query.Query("Hello", gray, jmv = new JMapViewer());
    TwitterSource twitterSource = new PlaybackTwitterSource(1.0);
    Application application = new Application();
    application.addQuery(query);
    twitterSource.addObserver(query);
    assertEquals(twitterSource.countObservers(), 1);
}



    private void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private <E> Set<E> set(E ... p) {
        Set<E> ans = new HashSet<>();
        for (E a : p) {
            ans.add(a);
        }
        return ans;
    }



    private class TestObserver implements Observer {
        private int nTweets = 0;



        @Override
        public void update(Observable o, Object arg) {
            Status s = (Status) arg;
            nTweets ++;
        }

        public int getNTweets() {
            return nTweets;
        }
    }
}
