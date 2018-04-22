import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;


@Mod.EventBusSubscriber(value = Side.CLIENT, modid = LogTitlesMod.MODID)
public class ClientEventHandler {
	static String lastTitle = "displayedTitle";
	static String lastSubTitle = "displayedSubTitle";

    /**
	 * Log titles on every tick
	 *
	 * @param event The event
	 */
	@SubscribeEvent
	public static void onClientTick(final TickEvent.ClientTickEvent event) {
		final Minecraft MINECRAFT = Minecraft.getMinecraft();

		if (event.phase == TickEvent.Phase.END && MINECRAFT.player != null) {
			try {
				// from http://www.minecraftforge.net/forum/topic/40032-how-to-get-the-title-and-sub-title-that-is-currently-on-screen/
				String title = (String) ReflectionHelper.findField(GuiIngame.class, "displayedTitle", "field_175201_x").get(Minecraft.getMinecraft().ingameGUI);
				String subTitle = (String) ReflectionHelper.findField(GuiIngame.class, "displayedSubTitle", "field_175200_y").get(Minecraft.getMinecraft().ingameGUI);
				
				if( (!title.trim().equals("") && !title.equals(lastTitle)) || 
					(!subTitle.trim().equals("") && !subTitle.equals(lastSubTitle))
				) {
					System.out.println("TITLE: " + title);
					System.out.println("SUB-TITLE: " + subTitle);
					lastTitle = title;
					lastSubTitle = subTitle;
				}
			} catch(Exception e) {
				System.out.println("Error: unable to get the title or displayed title field. \nError message: " + 
				e.getMessage() + "\n");
				e.printStackTrace();
			}
		}
	}
}
