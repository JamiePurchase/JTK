/*
 * JTK Audio Library
 * Version 1
 * Jamie Purchase
 */
package audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Jamie
 */
public class Audio
{
    private static int gap;
    private static HashMap<String, Clip> musicMap = new HashMap();
    private static Clip musicActive = null;
    private static HashMap<String, Clip> soundMap = new HashMap();
    private static Clip soundActive = null;
    
    public static void addMusic(String key, String path)
    {
        musicMap.put(key, loadFile(path));
    }
    
    public static void addSound(String key, String path)
    {
        soundMap.put(key, loadFile(path));
    }
    
    private static Clip loadFile(String path)
    {
        try
        {
            path = ClassLoader.getSystemResource(path).getPath();
            path = path.substring(1, path.length()).replaceAll("%20", " ");
            InputStream in = new FileInputStream(path);
            InputStream bin = new BufferedInputStream(in);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bin);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            return AudioSystem.getClip();
        }
        catch(Exception ex) {System.out.println(ex);}
        return null;
    }
    
    public static void playMusic(String key)
    {
        playMusic(key, gap);
    }
    
    public static void playMusic(String key, int pos)
    {
        Clip clip = musicMap.get(key);
        if(clip == null) return;
        if(clip.isRunning()) clip.stop();
        clip.setFramePosition(pos);
        while(!clip.isRunning()) {clip.start();}
        musicActive = clip;
    }

}