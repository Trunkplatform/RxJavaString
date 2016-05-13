package rx.observables;

import rx.Observable;
import rx.functions.Action1;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.Test;

public class CharacterObservableTest {

    @Test
    public void observableShouldBreakUpString() throws Exception {
        TestSubscriber<Character> t = new TestSubscriber<Character>();
        CharacterObservable.from("this is a string")
                .subscribe(t);
        t.assertNoErrors();
        t.assertCompleted();
        t.assertValues('t', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 's', 't', 'r', 'i', 'n', 'g');
    }

    @Test
    public void observableShouldStopEmittingOnUnsubscribe() throws Exception {
        final List<Character> cs = new ArrayList<Character>();
        TestSubscriber<Character> t = new TestSubscriber<Character>();
        CharacterObservable.from("this is a string")
                .doOnNext(new Action1<Character>() {
                    @Override
                    public void call(Character c) {
                        cs.add(c);
                    }
                })
                .take(5)
                .subscribe(t);
        t.assertNoErrors();
        t.assertCompleted();
        t.assertValues('t', 'h', 'i', 's', ' ');
        assertEquals(Arrays.asList('t', 'h', 'i', 's', ' '), cs);
    }

    @Test
    public void observableShouldCompleteImmediatelyWithEmptyString() throws Exception {
        TestSubscriber<Character> t = new TestSubscriber<Character>();
        CharacterObservable.from("")
                .subscribe(t);
        t.assertNoErrors();
        t.assertCompleted();
        t.assertNoValues();
    }

    @Test
    public void operatorShouldCompleteImmediatelyWithEmptyString() throws Exception {
        TestSubscriber<Character> t = new TestSubscriber<Character>();
        Observable.just("")
                .lift(CharacterObservable.fromStringObservable())
                .subscribe(t);
        t.assertNoErrors();
        t.assertCompleted();
        t.assertNoValues();
    }

    @Test
    public void operatorShouldSkipOverEmptyString() throws Exception {
        TestSubscriber<Character> t = new TestSubscriber<Character>();
        Observable.just("", "a", "", "b", "")
                .lift(CharacterObservable.fromStringObservable())
                .subscribe(t);
        t.assertNoErrors();
        t.assertCompleted();
        t.assertValues('a', 'b');
    }

    @Test
    public void operatorShouldCompleteImmediatelyWithEmptyUpstream() throws Exception {
        TestSubscriber<Character> t = new TestSubscriber<Character>();
        Observable.<String>empty()
                .lift(CharacterObservable.fromStringObservable())
                .subscribe(t);
        t.assertNoErrors();
        t.assertCompleted();
        t.assertNoValues();
    }

    @Test
    public void operatorShouldBreakUpString() throws Exception {
        TestSubscriber<Character> t = new TestSubscriber<Character>();
        Observable.just("this is ", "a string")
                .lift(CharacterObservable.fromStringObservable())
                .subscribe(t);
        t.assertValues('t', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 's', 't', 'r', 'i', 'n', 'g');
    }

    @Test
    public void operatorShouldPropogateErrors() throws Exception {
        TestSubscriber<Character> t = new TestSubscriber<Character>();
        RuntimeException exception = new RuntimeException();
        Observable.just("this is ")
                .concatWith(Observable.<String>error(exception))
                .concatWith(Observable.just("a string"))
                .lift(CharacterObservable.fromStringObservable())
                .subscribe(t);
        t.assertError(exception);
        t.assertValues('t', 'h', 'i', 's', ' ', 'i', 's', ' ');
    }

    @Test
    public void operatorShouldStopEmittingOnUnsubscribe() throws Exception {
        final List<Character> cs = new ArrayList<Character>();
        TestSubscriber<Character> t = new TestSubscriber<Character>();
        Observable.just("this is ", "a string")
                .lift(CharacterObservable.fromStringObservable())
                .doOnNext(new Action1<Character>() {
                    @Override
                    public void call(Character c) {
                        cs.add(c);
                    }
                })
                .take(5)
                .subscribe(t);
        t.assertValues('t', 'h', 'i', 's', ' ');
        assertEquals(Arrays.asList('t', 'h', 'i', 's', ' '), cs);
    }

    @Test
    public void operatorShouldSendBackPressureUpstream() throws Exception {
        TestSubscriber<Character> t = new TestSubscriber<Character>();
        t.requestMore(0);

        final int[] emitted = {0};

        Observable.just("t", "h", "", "i", "s is ", "a string")
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String ignore) {
                        emitted[0] += 1;
                    }
                })
                .lift(CharacterObservable.fromStringObservable())
                .subscribe(t);

        t.assertNoValues();

        t.requestMore(1);
        assertEquals(t.getOnNextEvents().size(), 1);
        assertEquals(emitted[0], 1);

        t.requestMore(1);
        assertEquals(t.getOnNextEvents().size(), 2);
        assertEquals(emitted[0], 2);

        t.requestMore(1);
        assertEquals(t.getOnNextEvents().size(), 3);
        assertEquals(emitted[0], 4);

        t.requestMore(1);
        assertEquals(t.getOnNextEvents().size(), 4);
        assertEquals(emitted[0], 5);

        t.requestMore(1);
        assertEquals(t.getOnNextEvents().size(), 5);
        assertEquals(emitted[0], 5);

        t.requestMore(1);
        assertEquals(t.getOnNextEvents().size(), 6);
        assertEquals(emitted[0], 5);

        t.requestMore(1);
        assertEquals(t.getOnNextEvents().size(), 7);
        assertEquals(emitted[0], 5);

        t.requestMore(1);
        assertEquals(t.getOnNextEvents().size(), 8);
        assertEquals(emitted[0], 5);

        t.requestMore(1);
        assertEquals(t.getOnNextEvents().size(), 9);
        assertEquals(emitted[0], 6);

    }
}
