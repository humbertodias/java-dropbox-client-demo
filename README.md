# DropBox Java Client

Demo application showing how to use dropbox API in java.


### Generate

The new button can be found on the App Console for any Dropbox API app:

![](doc/generate-access-token.png)

[https://www.dropbox.com/developers/apps](https://www.dropbox.com/developers/apps)

### Run

```
mvn compile exec:java -Dexec.mainClass="com.mycompany.app.App" -Dexec.args="REPLACE_FOR_ACCESS_TOKEN"
```

### Output

```
[INFO] --- exec-maven-plugin:1.5.0:java (default-cli) @ my-app ---
Account Name: Humberto Dias
[List Files]
/salvos
/primeiros passos com dropbox.pdf
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 9.080 s
[INFO] Finished at: 2017-02-09T00:04:35-02:00
[INFO] Final Memory: 14M/209M
[INFO] ------------------------------------------------------------------------
```

### Upload

![](doc/upload.png)


# References