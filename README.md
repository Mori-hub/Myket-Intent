
# Myket Intents

This is kind of About me where it includs several Intent and Links of Iranian App Store is called Myket 

## Features

- Myket links:
    - Rate and Comment
    - Download
    - Details
    - Developer 
- App : Icon ,Describes
- Profile : Developer ,Describes
- Developer's email
- Developer's Github account
- Developer's Instagram account id
- Developer's Whatsapp
- Add google play store link



## Installation

Install my-project with https://jitpack.io/

```bash
  dependencies {
	        implementation 'com.github.Mori-hub:Myket-Intent:0.1.15'
	}
```
    
## Usage/Examples

```javascript
    Aboutme_Myket v = new Aboutme_Myket(this);
    v.setHeaderImage(R.drawable.ba1); // It can get (int) , id.mipmap/drawable/color , png,xml
    v.setBodyImage(R.drawable.ba1);// It can get (int) , id.mipmap/drawable/color , png,xml

    v.setAppIcon(R.mipmap.ic_launcher_round);
    v.setAppCompany("خلاقیت کاری و کسب درآمد"); //It can get String
    v.setAppDescription(R.string.app_desc);

    v.setProfileName("Morteza"); //It can get String
    v.setProfileJob("App Developer");//It can get String
    v.setProfileDescription(R.string.prof_desc);
    v.setProfileImage(R.drawable.profile);// It can get (int) , id.mipmap/drawable/color , png,xml

    v.setMyketActive(true);// Myket links will be active by call this method. Don't need more!
    v.addEmail("e@t.com"); // Developer's email
    v.addGithub("Mori-Hub"); // Developer's Github account
    v.addInstagram("b72243"); // Developer's Instagram account id
    v.addWhatsapp("989000004"); // Developer's Whatsapp
    v.addGoogle();// Add google play store link
    setContentView(v); // Don't forget set it !
```


## Screenshots
|Google Store| Maket Store |
|------------|-------------|
| <img src="https://user-images.githubusercontent.com/53067774/160182888-52b106ba-7003-4886-a0ee-25463c2dd15d.jpg" width="250"> | <img src="https://user-images.githubusercontent.com/53067774/160182899-510a528e-4a29-4a42-a752-69e8ea68a745.jpg" width="250"> |



## 🚀 About Me
I'm a full stack developer...


## 🛠 Skills
Java, Kotlin, CSS....


## Tech Stack

**Important:** For Gradle 7.2 & jitpack.io Please use this way : https://stackoverflow.com/a/71603699/12272687

**Update:** March 2022


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/Mori-hub)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/)
[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/)

