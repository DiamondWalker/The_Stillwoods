package stillwoods.util;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;

public class TextUtil {
    public static Component[] getTranslatedMessages(String key) {
        int entries = 0;
        Language lang = Language.getInstance();
        while (lang.has(key + "." + entries)) {
            entries++;
        }

        Component[] result = new Component[entries];
        for (int i = 0; i < entries; i++) {
            result[i] = Component.translatable(key + "." + i);
        }
        return result;
    }
}
