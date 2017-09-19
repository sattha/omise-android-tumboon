# omise-android-tumboon

### Building Release Version.

  - Download two files keep in your local build machine
  - Open project navigate to gradle.properties
  - Update Key.signing corresponding to path that you keep downloaded files

### Proguard

Run app in release mode to enable proguard.

### Running Application

Open app build.gradle and change URL_WEB_END_POINT to specific API endpoint

Install the dependencies and devDependencies and start the server.

```sh
  productFlavors {
        prod {
            dimension "default"
            buildConfigField("String",
                    "URL_WEB_END_POINT", "\"http://192.168.101.92:3000/\"")
        }
        dev {
            dimension "default"
            buildConfigField("String",
                    "URL_WEB_END_POINT", "\"http://192.168.101.92:3000/\"")
        }
    }
```


