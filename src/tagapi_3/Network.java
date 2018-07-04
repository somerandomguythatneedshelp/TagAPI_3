/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tagapi_3;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author ammar
 */
class Network {

    //this function needs to change in order to make it dynamic
    //all web urls come here...
    public final String https_libraries_minecraft_net = "https://libraries.minecraft.net";
    public final String http_resources_download_minecraft_net = "http://resources.download.minecraft.net";
    public final String https_s3_amazonaws_com_Minecraft_Download_versions = "https://s3.amazonaws.com/Minecraft.Download/versions";
    public final String https_api_mojang_com_users_profiles_minecraft = "https://api.mojang.com/users/profiles/minecraft";
    public final String https_raw_githubusercontent_com = "https://raw.githubusercontent.com/ammarx/TagAPI_3/master/api_meta?time=" + (Math.random() * 100);

    public void downloadAPIMeta(String OS) {
        try {
            Utils utils = new Utils();
            URL url = new URL(https_raw_githubusercontent_com);
            File file = new File(utils.getMineCraftLocation(OS) + "/api_meta");
            FileUtils.copyURLToFile(url, file);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void downloadProfile(String OS, String _username) {
        try {
            Utils utils = new Utils();
            URL url = new URL(https_api_mojang_com_users_profiles_minecraft + "/" + _username);
            File file = new File(utils.getMineCraftLocation(OS) + "/" + _username + ".json");
            if (file.exists()) {
                //do not download..
                System.out.println("File Exists!");
            } else {
                FileUtils.copyURLToFile(url, file);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void downloadLibraries(String OS, String _url, String _path, Boolean ForceDownload) {
        try {
            Utils utils = new Utils();
            URL url = new URL(_url);
            File file = new File(utils.getMineCraftLibrariesLocation(OS) + "/" + _path);
            if (ForceDownload == true) {
                FileUtils.copyURLToFile(url, file);
            } else if (file.exists()) {
                System.out.println("File Exists! - Skipping download");
            } else {
                FileUtils.copyURLToFile(url, file);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void downloadAssetsObjects(String OS, String folder, String _hash) {
        //resources.download.minecraft.net/4b/4b90ff3a9b1486642bc0f15da0045d83a91df82e
        try {
            Utils utils = new Utils();
            URL url = new URL(http_resources_download_minecraft_net + "/" + folder + "/" + _hash);
            File file = new File(utils.getMineCraftAssetsObjectsLocation(OS) + "/" + folder + "/" + _hash);
            if (file.exists() && utils.getSHA_1(file.toString()).equals(_hash)) {
                //do not download..
                System.out.println("File Exists!");
                System.out.println("Hash Verified!");
            } else {
                //System.out.println("Calculated Hash:" + utils.getSHA_1(file.toString()));
                FileUtils.copyURLToFile(url, file);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void downloadLaunchermeta(String OS, String _url, String version, Boolean ForceDownload) {
        try {
            Utils utils = new Utils();
            URL url = new URL(_url);
            File file = new File(utils.getMineCraftAssetsIndexes_X_json(OS, version));
            if (ForceDownload == true) {
                FileUtils.copyURLToFile(url, file);
            } else if (file.exists()) {
                //do not download..
                System.out.println("File Exists! - Skipping download");
            } else {
                FileUtils.copyURLToFile(url, file);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    
    public void downloadMinecraftJar_fallBack_v1(String OS, String _url, String version, Boolean ForceDownload)
    {
        try {
            Utils utils = new Utils();
            URL url = new URL(_url);
            File file = new File(utils.getMineCraft_Versions_X_X_jar_Location(OS, version));
            if (ForceDownload == true) {
                FileUtils.copyURLToFile(url, file);
            } else if (file.exists()) {
                //do not download..
                System.out.println("File Exists! - Skipping download");
            } else {
                FileUtils.copyURLToFile(url, file);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public int downloadMinecraftJar(String OS, String version, Boolean ForceDownload) {
        try {
            Utils utils = new Utils();
            URL url = new URL(https_s3_amazonaws_com_Minecraft_Download_versions + "/" + version + "/" + version + ".jar");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            if (httpCon.getResponseCode() == 403 || httpCon.getResponseCode() == 404)
            {
                //use client.jar scheme
                return 1;
            }
            File file = new File(utils.getMineCraft_Versions_X_X_jar_Location(OS, version));
            if (ForceDownload == true) {
                FileUtils.copyURLToFile(url, file);
            } else if (file.exists()) {
                //do not download..
                System.out.println("File Exists! - Skipping download");
            } else {
                FileUtils.copyURLToFile(url, file);
            }
        } catch (Exception e) {
            System.out.print(e);
            return 1;
        }
        return 0;
    }

    public void downloadVersionManifest(String _filepath) {
        try {
            URL url = new URL("https://launchermeta.mojang.com/mc/game/version_manifest.json");
            File file = new File(_filepath);
            FileUtils.copyURLToFile(url, file);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void downloadVersionJson(String OS, String _url, String versionnumber) {
        try {
            Utils utils = new Utils();
            URL url = new URL(_url);
            File file = new File(utils.getMineCraft_Versions_X_X_json(OS, versionnumber));
            /*if (file.exists()){
                //do not download..
                System.out.println("File Exists!");
            } else {
                FileUtils.copyURLToFile(url, file);
            }*/
            FileUtils.copyURLToFile(url, file);

        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
