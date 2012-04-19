## RT-Jasper

To use RT-Jasper you need to first install [RT-REST](https://bboksa.github.com/RT-REST "RT-REST") to your local maven repository:

```
git clone git@github.com:bboksa/RT-REST.git
cd RT-REST
mvn install
```

After that you can clone RT-Jasper and build the distribution files:

```
git clone git@github.com:bboksa/RT-Jasper.git
cd RT-Jasper
mvn clean assembly:assembly
```

Now you need to copy the resulting ZIP file to your server and extract all files from it:

```
unzip jasper-rt-distribution.zip
```

Take a look at jasper-rt-distribution/build.xml to make sure all properties (espacially webAppDir) are set correctly and then execute:

```
ant -f jasper-rt-distribution/build.xml clean deploy
/etc/init.d/tomcat6 restart
```

You should now be able to configure a RT Data Source as well as define RTRIQL (= RT Rest Interface Query Language) queries.





