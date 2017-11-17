Reference：
[https://github.com/grafana/grafana-docker]()

Sample：

```
docker run --name grafana -d \
-p EXPOSED_PORT:3000 \
-v /root/ops-deploy/grafana/ldap.toml:/etc/grafana/ldap.toml \
-v /var/lib/grafana:/var/lib/grafana \
-e "GF_SERVER_DOMAIN=your-grafana-domain.com" \
-e "GF_SERVER_HTTP_PORT=EXPOSED_PORT" \
-e "GF_AUTH_LDAP_ENABLED=true" \
-e "GF_AUTH_LDAP_CONFIG_FILE=ldap.toml" \
-e "GF_USERS_ALLOW_SIGN_UP=false" \
-e "GF_LOG_LEVEL=debug" \
-e "GF_AWS_PROFILES=your_profile1 your_profile2" \
-e "GF_AWS_your_profile1_ACCESS_KEY_ID=access_key_id1" \
-e "GF_AWS_your_profile1_SECRET_ACCESS_KEY=access_key1" \
-e "GF_AWS_your_profile1_REGION=ap-northeast-1" \
-e "GF_AWS_your_profile2_ACCESS_KEY_ID=access_key_id2" \
-e "GF_AWS_your_profile2_SECRET_ACCESS_KEY=access_key2" \
-e "GF_AWS_your_profile2_REGION=us-east-1" \
-e "GF_INSTALL_PLUGINS=grafana-piechart-panel" \
grafana/grafana:4.2.0
```
