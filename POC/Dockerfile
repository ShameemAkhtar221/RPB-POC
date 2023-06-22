#Step 0: Choose base
FROM openjdk:11
#Step 1 : Install the pre-requisite
RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y p7zip \
   p7zip-full \
   unace \
   zip \
   unzip \
   bzip2


#Version numbers
# ARG FIREFOX_VERSION=78.0.2
ARG CHROME_VERSION=111.0.5563.64
ARG CHROMDRIVER_VERSION=111.0.5563.64
# ARG FIREFOXDRIVER_VERSION=0.26.0

ENV CHROMEDRIVER_PATH /usr/local/bin/chromedriver
ENV FIREFOX_DRIVER_PATH /usr/local/bin/chromedriver
COPY src/main/java/resources/data.properties /usr/src/app/data.properties

# Killing running chrome process
RUN pkill -f chromedriver
RUN pkill -f chrome


#Step 2: Install Chrome
RUN curl http://dl.google.com/linux/chrome/deb/pool/main/g/google-chrome-stable/google-chrome-stable_$CHROME_VERSION-1_amd64.deb -o /chrome.deb
RUN apt-get update && apt-get install -y \
     fonts-liberation \
        libasound2 \
        libatk-bridge2.0-0 \
        libatk1.0-0 \
        libatspi2.0-0 \
        libcairo2 \
        libcups2 \
        libdbus-1-3 \
        libdrm2 \
        libgbm1 \
        libglib2.0-0 \
        libgtk-3-0 \
        libnspr4 \
        libnss3 \
        libpango-1.0-0 \
        libu2f-udev \
        libvulkan1 \
        libx11-6 \
        libxcb1 \
        libxcomposite1 \
        libxdamage1 \
        libxext6 \
        libxfixes3 \
        libxkbcommon0 \
        libxrandr2 \
        xdg-utils \
        --no-install-recommends \
        && rm -rf /var/lib/apt/lists/*

RUN dpkg -i /chrome.deb
RUN rm /chrome.deb

# RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
#     && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
#     && apt-get update \
#     && apt-get install ca-certificates \
#     && apt-get install -y google-chrome-stable=${CHROME_VERSION}-1 \
#     && rm -rf /var/lib/apt/lists/* /var/cache/apt/*

#Step 3: Install chromedriver for Selenium
RUN apt-get update && apt-get install -y curl unzip && \
    curl -O https://chromedriver.storage.googleapis.com/${CHROMDRIVER_VERSION}/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip && \
    mv chromedriver $CHROMEDRIVER_PATH && \
    chmod +x $CHROMEDRIVER_PATH && \
    rm chromedriver_linux64.zip

RUN google-chrome --version

# #Step 4 : Install firefox
# RUN wget --no-verbose -O /tmp/firefox.tar.bz2 https://download-installer.cdn.mozilla.net/pub/firefox/releases/$FIREFOX_VERSION/linux-x86_64/en-US/firefox-$FIREFOX_VERSION.tar.bz2 \
#  && bunzip2 /tmp/firefox.tar.bz2 \
#  && tar xvf /tmp/firefox.tar \
#  && mv /firefox /opt/firefox-$FIREFOX_VERSION \
#  && ln -s /opt/firefox-$FIREFOX_VERSION/firefox /usr/bin/firefox
# #Step 5: Install Geckodriver
# RUN wget https://github.com/mozilla/geckodriver/releases/download/v$FIREFOXDRIVER_VERSION/geckodriver-v$FIREFOXDRIVER_VERSION-linux64.tar.gz \
#    && tar -xf geckodriver-v0.26.0-linux64.tar.gz \
#    && cp geckodriver /app/bin/geckodriver
# RUN chmod +x /app/bin/geckodriver
#Step 6: Install Maven
# 1- Define Maven version
ARG MAVEN_VERSION=3.6.3
# 2- Define a constant with the working directory
ARG USER_HOME_DIR="/root"


# 3- Define the SHA key to validate the maven download
ARG SHA=c35a1803a6e70a126e80b2b3ae33eed961f83ed74d18fcd16909b2d44d7dada3203f1ffe726c17ef8dcca2dcaa9fca676987befeadc9b9f759967a8cb77181c0


# 4- Define the URL where maven can be downloaded from
ARG BASE_URL=https://mirrors.estointernet.in/apache/maven/maven-3/${MAVEN_VERSION}/binaries


# 5- Create the directories, download maven, validate the download, install it, remove downloaded file and set links
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
 && echo "Downlaoding maven" \
 && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 \
 && echo "Checking download hash" \
 && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
 \
 && echo "Unziping maven" \
 && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
 \
 && echo "Cleaning and setting links" \
 && rm -f /tmp/apache-maven.tar.gz \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn


# 6- Define environmental variables required by Maven, like Maven_Home directory and where the maven repo is located
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
#Step 7: Copy our project
COPY . /app
#Making our working directory as /app
WORKDIR /app

RUN mvn package
#  -DskipTests

ENTRYPOINT ["mvn", "test"]