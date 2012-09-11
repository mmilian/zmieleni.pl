dataSource {
	pooled = true
	driverClassName = "org.h2.Driver"
	username = "sa"
	password = ""
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
		}
	}
	production {
		def envVar = System.env.VCAP_SERVICES
		def credentials = envVar?grails.converters.JSON.parse(envVar)["mysql-5.1"][0]["credentials"]:null
	 
		dataSource {
		   dbCreate = "update"
		   url =  credentials?"jdbc:mysql://${credentials.hostname}:${credentials.port}/${credentials.name}?useUnicode=yes&characterEncoding=UTF-8":""
		   username = credentials?credentials.username:""
		   password = credentails?credentials.password:""
		}
		
		
//		dataSource {
//			dbCreate = "update"
//			url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
//			pooled = true
//			properties {
//				maxActive = -1
//				minEvictableIdleTimeMillis=1800000
//				timeBetweenEvictionRunsMillis=1800000
//				numTestsPerEvictionRun=3
//				testOnBorrow=true
//				testWhileIdle=true
//				testOnReturn=true
//				validationQuery="SELECT 1"
//			}
//		}
	}
}
