package by.teachmeskills.homeworks.hw_10032023.part1.enums;

public enum Alphabet {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P,
    Q, R, S, T, U, V, W, X, Y, Z;

    public int getLetterPosition() {
        return this.ordinal() + 1;
    }
}
