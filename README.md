# Introduction
libgdx playground application

## Troubleshooting ##
`java.lang.IllegalStateException: GLFW windows may only be created on the main thread and that thread must be the first thread in the process.`

Add `-XstartOnFirstThread` VM arg
### Lambda ###