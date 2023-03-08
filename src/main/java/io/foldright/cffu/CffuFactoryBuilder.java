package io.foldright.cffu;

import edu.umd.cs.findbugs.annotations.ReturnValuesAreNonnullByDefault;
import org.jetbrains.annotations.Contract;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.Executor;

@ThreadSafe
@ParametersAreNonnullByDefault
@ReturnValuesAreNonnullByDefault
public final class CffuFactoryBuilder {
    private final Executor defaultExecutor;

    private volatile boolean forbidObtrudeMethods = false;

    private CffuFactoryBuilder(Executor defaultExecutor) {
        this.defaultExecutor = defaultExecutor;
    }

    ////////////////////////////////////////////////////////////////////////////////
    // Builder Methods
    ////////////////////////////////////////////////////////////////////////////////

    @Contract(pure = true)
    @SuppressWarnings("ConstantValue")
    public static CffuFactoryBuilder newCffuFactoryBuilder(Executor defaultExecutor) {
        if (defaultExecutor == null) throw new NullPointerException("defaultExecutor is null");
        return new CffuFactoryBuilder(defaultExecutor);
    }

    public CffuFactoryBuilder forbidObtrudeMethods(boolean forbid) {
        this.forbidObtrudeMethods = forbid;
        return this;
    }

    @Contract(pure = true)
    public CffuFactory build() {
        return new CffuFactory(defaultExecutor, forbidObtrudeMethods);
    }
}
