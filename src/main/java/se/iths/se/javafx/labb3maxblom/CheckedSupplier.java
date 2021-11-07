package se.iths.se.javafx.labb3maxblom;

public interface CheckedSupplier <R> {
        R get() throws Exception;

        static <R> R wrap(CheckedSupplier<R> supplier) {
            try {
                return supplier.get();
            } catch (Exception e) {
                throw new RuntimeException();
            }}
}
