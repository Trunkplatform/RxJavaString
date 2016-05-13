package rx.observables;

import rx.Observable;
import rx.internal.operators.OnSubscribeStringToChar;
import rx.internal.operators.OperatorStringToChar;

public class CharacterObservable {

    /**
     * Converts a String into an Observable that emits the Characters in the String.
     * The returned Observable natively supports
     * <a href="https://github.com/ReactiveX/RxJava/wiki/Backpressure">backpressure</a>.
     * <p>
     * <img width="640" height="315" src="https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/from.png" alt="">
     *
     * @param s source string
     * @return  an Observable of the characters in the string
     * @see <a href="https://github.com/ReactiveX/RxJava/wiki/Creating-Observables#from">RxJava wiki: from</a>
     */
    public static Observable<Character> from(String s) {
        return Observable.create(new OnSubscribeStringToChar(s));
    }

    /**
     * An Operator to <a href="http://reactivex.io/RxJava/javadoc/rx/Observable.html#lift(rx.Observable.Operator)">lift</a>
     * an Observable that emits Strings to an Observable that emits the
     * Characters in each of the Strings.
     * The returned Operator natively supports
     * <a href="https://github.com/ReactiveX/RxJava/wiki/Backpressure">backpressure</a>.
     * <p>
     * <img width="640" height="315" src="https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/operator.png" alt="">
     *
     * @return  an Operator that will lift an Observable to String to an Observable of Characters
     * @see <a href="https://github.com/ReactiveX/RxJava/wiki/Implementing-Your-Own-Operators">RxJava wiki: Implementing Your Own Operators</a>
     */
    public static Observable.Operator<Character, String> fromStringObservable() {
        return new OperatorStringToChar();
    }
}
