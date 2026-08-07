# How to get ClipMind on your Android phone (no computer needed)

Because you only have a phone, we use **GitHub Actions** to build the app in the cloud. You then download the APK and install it.

## Step-by-step

### 1. Trigger the build (or wait for the automatic one)

1. Open this link on your phone:  
   **https://github.com/jay117king/clipmind/actions**

2. Tap the latest **“Build Debug APK”** workflow run.

3. If none is running yet, tap **“Run workflow”** → **Run workflow** (this is the manual button).

4. Wait 3–6 minutes for the build to finish (it will show a green checkmark).

### 2. Download the APK

1. Once the workflow is finished, scroll down to the **Artifacts** section.
2. Tap **clipmind-debug-apk**.
3. Your phone will download a zip file containing the APK.

### 3. Install the APK

1. Open the downloaded zip and extract the `.apk` file.
2. Tap the APK.
3. If Android says “Install unknown apps”, allow it for your browser or file manager.
4. Tap **Install**.

You should now see **ClipMind** in your app drawer.

---

## Alternative: GitHub Codespaces (real cloud Android Studio)

If the Actions build fails or you want a full development environment in the browser:

1. Go to https://github.com/jay117king/clipmind
2. Tap the green **Code** button → **Codespaces** → **Create codespace on main**
3. Wait for the cloud computer to start
4. Once it loads, you can open the `mobile` folder and build from there

(Note: Free Codespaces hours are limited.)

---

## Current limitations of the APK

- It is a **debug** build (not for Play Store)
- No custom launcher icon yet (Android will show a default one)
- Screenshot detection, OCR and AI chat are still skeletons
- You will mainly see the Chat screen and the sample Clips list

As we add more features, every push to `main` will automatically produce a new APK you can download the same way.
