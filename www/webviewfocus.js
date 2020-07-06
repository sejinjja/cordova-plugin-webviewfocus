module.exports = {
  requestFocus() {
    return new Promise((resolve, reject) => {
      cordova.exec(resolve, reject, "Webviewfocus", "requestFocus", [])
    })
  }
}
