import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = LogTitlesMod.MODID, version = LogTitlesMod.VERSION)
public class LogTitlesMod
{
    public static final String MODID = "logtitlesmod";
    public static final String VERSION = "1.0";
    public static final String NAME = "The logging titles mod";
    
    @EventHandler
    public void init(FMLInitializationEvent event){}
}
