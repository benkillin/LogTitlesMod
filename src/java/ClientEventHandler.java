import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;


@Mod.EventBusSubscriber(value = Side.CLIENT, modid = LogTitlesMod.MODID)
public class ClientEventHandler {
	static String lastTitle = "IMPOSSIBRU";
	static String lastSubTitle = "IMPOSSIBRU";

	public static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }

    /**
	 * Log titles on every tick
	 *
	 * @param event The event
	 */
	@SubscribeEvent
	public static void onClientTick(final TickEvent.ClientTickEvent event) {
		final Minecraft MINECRAFT = Minecraft.getMinecraft();

		if (event.phase == TickEvent.Phase.END && MINECRAFT.player != null) {
			GuiIngame gui = MINECRAFT.ingameGUI;

			try {
				String title = getField(gui.getClass(), "displayedTitle").get(gui).toString();
				String subTitle = getField(gui.getClass(), "displayedSubTitle").get(gui).toString();

				if( (!title.trim().equals("") && !title.equals(lastTitle)) || 
					(!subTitle.trim().equals("") && !subTitle.equals(lastSubTitle))
				) {
					System.out.println("TITLE: " + title);
					System.out.println("SUB-TITLE: " + subTitle);
					lastTitle = title;
					lastSubTitle = subTitle;
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
