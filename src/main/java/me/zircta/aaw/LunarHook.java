package me.zircta.aaw;

import net.weavemc.loader.api.Hook;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class LunarHook extends Hook {
    @Override
    public void transform(@NotNull ClassNode node, @NotNull AssemblerConfig cfg) {
        if (node.name.startsWith("com/moonsworth")) {
            for (MethodNode method : node.methods) {
                if (method.name.equals("check")) {
                    InsnList instructions = method.instructions;
                    for (AbstractInsnNode instruction : instructions) {
                        if (instruction instanceof JumpInsnNode jumpInsn) {
                            if (jumpInsn.getOpcode() == Opcodes.IFNE) { // IFNE is the opcode for "if not empty"
                                jumpInsn.setOpcode(Opcodes.IFEQ); // Change to "if empty"
                            }
                        }
                    }
                }
            }
        }
    }
}
