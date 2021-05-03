package org.jetbrains.jwm;

import java.util.function.*;
import lombok.*;
import org.jetbrains.annotations.*;

@ToString @EqualsAndHashCode
public class Window implements AutoCloseable {
    static { Library.staticLoad(); }

    public final long _ptr;

    public Window() {
        long ptr = _nMake();
        if (ptr == 0)
            throw new IllegalArgumentException("org.jetbrains.jwm.Window: Can't wrap nullptr");
        _ptr = ptr;
    }

    @NotNull @Contract("-> this")
    public Window setEventListener(@NotNull Consumer<Event> eventListener) {
        _nSetEventListener(_ptr, eventListener);
        return this;
    }

    @NotNull @Contract("-> this")
    public Window show() {
        _nShow(_ptr);
        return this;
    }

    @Override
    public void close() {
        _nClose(_ptr);
    }

    @ApiStatus.Internal public static native long _nMake();
    @ApiStatus.Internal public static native void _nSetEventListener(long ptr, Consumer<Event> listener);
    @ApiStatus.Internal public static native void _nShow(long ptr);
    @ApiStatus.Internal public static native void _nClose(long ptr);
}