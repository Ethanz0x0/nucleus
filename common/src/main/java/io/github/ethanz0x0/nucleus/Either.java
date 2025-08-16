package io.github.ethanz0x0.nucleus;

import java.util.function.Function;

/**
 * A container that holds either a {@code Left} value or a {@code Right} value
 *
 * @param <L>
 *        The type of the {@code Left} value
 * @param <R>
 *        The type of the {@code Right} value
 */
public class Either<L, R> {

    /**
     * Creates a {@code Either} instance that only includes a {@code Left} value.
     *
     * @param left
     *        The left value
     * @return
     *         A {@code Either} instance that only includes a {@code Left} value
     * @param <L>
     *        The type of the {@code Left} value
     * @param <R>
     *        The type of the {@code Right} value
     */
    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(left, null);
    }

    /**
     * Creates a {@code Either} instance that only includes a {@code Right} value.
     *
     * @param right
     *        The right value
     * @return
     *         A {@code Either} instance that only includes a {@code Right} value
     * @param <L>
     *        The type of the {@code Left} value
     * @param <R>
     *        The type of the {@code Right} value
     */
    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(null, right);
    }

    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public L getLeftOrCompute(Function<R, L> function) {
        if (isLeft()) {
            return left;
        }
        return function.apply(right);
    }

    public R getRightOrCompute(Function<L, R> function) {
        if (isRight()) {
            return right;
        }
        return function.apply(left);
    }

}
