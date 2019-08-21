use(function () {

    var testStr= this.value;

    var retValue = currentPage.path.toLowerCase().contains(testStr.toLowerCase());

    return {
        hasString: retValue
    };
});