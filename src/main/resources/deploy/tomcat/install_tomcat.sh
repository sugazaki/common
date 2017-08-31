TOMCAT=apache-tomcat-7.0.72
TOMCAT_WEBAPPS=$TOMCAT/webapps
TOMCAT_ARCHIVE=$TOMCAT.tar.gz
TOMCAT_URL=http://192.168.2.233:8080/wiki_vivo_internet/uploads/en/2017/7/apache-tomcat-7.0.72.tar-14161548.gz

if [ ! -e $TOMCAT ]; then
    if [ ! -r $TOMCAT_ARCHIVE ]; then
	if [ -n "$(which curl)" ]; then
	    curl -o $TOMCAT.tar.gz $TOMCAT_URL
	elif [ -n "$(which wget)" ]; then
	    wget $TOMCAT_URL
	fi
    fi

    if [ ! -r $TOMCAT_ARCHIVE ]; then
	echo "Tomcat could not be downloaded." 1>&2
	echo "Verify that eiter curl or wget is installed." 1>&2
	echo "If they are, check your internet connection and try again." 1>&2
	echo "You may also download $TOMCAT_ARCHIVE and place it in this folder." 1>&2
	exit 1
    fi

    tar -zxf $TOMCAT_ARCHIVE
    rm $TOMCAT_ARCHIVE
fi

if [ ! -w $TOMCAT -o ! -w $TOMCAT_WEBAPPS ]; then
    echo "$TOMCAT and $TOMCAT_WEBAPPS must be writable." 1>&2
    exit 1
fi

if [ ! -e tomcat ]; then
  ln -s $TOMCAT tomcat
fi