package me.zircta.aaa;

import net.weavemc.loader.api.Hook;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class LunarHook extends Hook {
    @Override
    public void transform(@NotNull ClassNode node, @NotNull AssemblerConfig cfg) {
        // Check if the class belongs to the "com.moonsworth.lunar" package
        if (node.name.startsWith("com/moonsworth/lunar")) {
            // Iterate through all methods in the class
            for (MethodNode method : node.methods) {
                // Check if the method is named "check"
                if (method.name.equals("check")) {
                    // Iterate through each instruction in the method
                    for (AbstractInsnNode insn : method.instructions) {
                        // Check if the instruction is a jump instruction
                        if (insn instanceof JumpInsnNode jumpInsn) {
                            // Check if the jump instruction is of type IFNE (if not equal to zero)
                            if (jumpInsn.getOpcode() == Opcodes.IFNE) {
                                // Replace IFNE with IFEQ (if equal to zero)
                                jumpInsn.setOpcode(Opcodes.IFEQ);
                                // Thus bypassing the Agent check.
                            }
                        }
                    }
                }
            }
        }
    }
}
