package de.bwaldvogel.mongo.exception;

public enum ErrorCode {
    BadValue(2),
    CursorNotFound(3),
    FailedToParse(9),
    TypeMismatch(14),
    IndexNotFound(27),
    PathNotViable(28),
    ConflictingUpdateOperators(40),
    DollarPrefixedFieldName(52),
    CommandNotFound(59),
    ImmutableField(66),
    IndexOptionsConflict(85),
    CannotIndexParallelArrays(171),
    DuplicateKey(11000);

    private final int id;
    ErrorCode(int id) { this.id = id; }
    public int getValue() { return id; }
}
