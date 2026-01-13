FROM ubuntu:latest

LABEL org.opencontainers.image.source="https://github.com/daversomethingsomething/jenkins-multiple-github-checkout/"
LABEL org.opencontainers.image.path=".devcontainer/Dockerfile"
##LABEL org.opencontainers.image.title=""
LABEL org.opencontainers.image.description="An Ubuntu based image for developing Jenkins Groovy Shared Libraries with MkDocs documentation."
LABEL org.opencontainers.image.authors="David L. Armstrong (@daversomethingsomething)"
LABEL org.opencontainers.image.licenses="Apache-2.0"
#LABEL org.opencontainers.image.documentation="<gh pages url>"

# Set up a baseline install before proceeding with our customizations
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update -y \
 && apt-get install --no-install-recommends -y \
      ca-certificates \
      gnupg \
      ssh \
      git \
      git-lfs \
      sudo \
      curl \
      wget \
      neovim \
      less \
      python3 \
      python-is-python3 \
      python3-pip \
      python3-venv \
      groovy \
      groovy-doc \
      gradle

#RUN groupadd -g 1000 luser && \
#    useradd -m -u 1000 -g 1000 -s /bin/bash luser && \
#    echo "luser ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers
#
#SHELL ["/bin/bash", "-c"]
#
## Set the default user
#USER luser

# Make sure VIRTUAL_ENV PATH is always ahead of any other path to python
ENV VIRTUAL_ENV=/opt/mkdocs-venv
RUN python3 -m venv $VIRTUAL_ENV
ENV PATH="$VIRTUAL_ENV/bin:$PATH"

# Rather than have VS Code postInstall mkdocs we'll bake it into our image
COPY python-requirements.txt* /tmp
RUN if [ -f "/tmp/python-requirements.txt" ]; then \
      pip install --no-cache-dir -r /tmp/python-requirements.txt; \
      rm /tmp/python-requirements.txt; \
    fi;
