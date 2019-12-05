SOURCE_DIR := "./aws/${APP_GROUP}/cf"
CONFIG_DIR := "./build-config/${ENV}/${APP_GROUP}"
DIST_DIR := "./dist-${APP_GROUP}"
TAR_FILE := "${APP_GROUP}-${BUILD_VERSION}.tgz"

# Validate Environment var is set
guard-%:
	@echo "--> Confirming environment variable ${*} is set"
	@if [ "${${*}}" = ""]; then \
		echo "!! Environment variable $* not set !!"; \
		exit 1; \
	fi
	
# Make the dist folder
make-dist-dir: guard-DIST_DIR
	rm -rf ${DIST_DIR}
	mkdir ${DIST_DIR}

# Package the template using the Salt configuration
package-APP_GROUP: guard-BUILD_IMAGE_PATH guard-BUILD_IMAGE_VERSION make-dist-dir
	@echo "Packaging for APP_GROUP : ${APP_GROUP} for Environment: ${ENV}"
	pwd
	@echo "Source Dir : ${SOURCE_DIR}, Config Directory: ${CONFIG_DIR}"
	cp -R ${SOURCE_DIR}/* ${DIST_DIR}/
	cp -R ${CONFIG_DIR}/* ${DIST_DIR}/
	ls -lrt ${DIST_DIR}
	sed -i -e "s|@@BUILD_IMAGE_PATH@@|${BUILD_IMAGE_PATH}|g" ${DIST_DIR}/cf.config
	sed -i -e "s|@@BUILD_IMAGE_VERSION@@|${BUILD_IMAGE_VERSION}|g" ${DIST_DIR}/cf.config
	echo "parameter.forceUpdate=`date`" >> ${DIST_DIR}/cf.config
	tar -zcvf ${TAR_FILE} -C ${DIST_DIR} .
	
publish-APP_GROUP: package-APP_GROUP
	ls -lrt
	@echo "Publishing Package : ${TAR_FILE} to Artifactory: https://artifacts.wellmanage.com/artifactory/${ARTIFACT_GROUP}/${APP_GROUP}"
	curl -i -k -u "${ARTIFACT_ACCESS_KEY}" -T $(TAR_FILE) "https://artifacts.wellmanage.com/artifactory/${ARTIFACT_GROUP}/${APP_GROUP}/${APP_GROUP}-${BUILD_VERSION}.tgz"

aws-deploy-APP_GROUP: guard-APP_GROUP guard-ENV publish-APP_GROUP
	@echo "Doing AWS Deployment for APP_GROUP: ${APP_GROUP}. VPC:${VPC}, Environment: ${ENV}"
	
	@if [ "${ENV}" != "prod" ]; then \
		@echo "Environment is PROD not going to execute AWS Deploy "; \
		awsWmcDeploy ${VPC} ${APP_GROUP} "${ARTIFACTORY_URL}/${ARTIFACT_GROUP}/${APP_GROUP}/${APP_GROUP}-${BUILD_VERSION}.tgz"

build-docker:
	ls -lrt
	docker build --build-arg WMC_ENV=${ENV} -t ${BUILD_IMAGE_PATH} .

test-docker:
	// docker run --rm -v `pwd`/test:`pwd`/test ${BUILD_IMAGE_PATH} /usr/local/bin/goss validate -f junit > `pwd`/test/results.xml
	@echo "Tests go here"
	