package in.praj.demo;

import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CFieldAddress;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;
import org.graalvm.word.PointerBase;

import java.util.Collections;
import java.util.List;

@CContext(Main.Directives.class)
public class Main {
    public static final class Directives implements CContext.Directives {
        @Override
        public List<String> getHeaderFiles() {
            return Collections.singletonList("<sys/utsname.h>");
        }
    }

    @CStruct("struct utsname")
    interface Utsname extends PointerBase {
        @CFieldAddress CCharPointer sysname();
        @CFieldAddress CCharPointer nodename();
        @CFieldAddress CCharPointer release();
        @CFieldAddress CCharPointer version();
        @CFieldAddress CCharPointer machine();
    }

    @CFunction
    static native int uname(Utsname buf);

    static void print(String key, CCharPointer value) {
        System.out.println(key + ": " + CTypeConversion.toJavaString(value));
    }

    public static void main(String[] args) {
        var info = StackValue.get(Utsname.class);
        if (uname(info) == -1) {
            System.out.println("Error loading system information");
            System.exit(-1);
        }

        print("Kernel name", info.sysname());
        print("Node name", info.nodename());
        print("Kernel release", info.release());
        print("Kernel version", info.version());
        print("Machine", info.machine());
    }
}
