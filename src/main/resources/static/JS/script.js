console.log("script loaded")
let currentTheme = getTheme();
changeTheme();
function changeTheme()
{
    document.querySelector('html').classList.add(currentTheme);

    const changeTheme = document.querySelector('#theme_change_button');
    changeTheme.querySelector('span').textContent = currentTheme == 'light'?'Dark':"Light";

    changeTheme.addEventListener("click" , ()=>{
        console.log("theme changed");
        //removing current theme
        document.querySelector('html').classList.remove(currentTheme)
        if(currentTheme == "dark")
        {
            currentTheme="light";
        }
        else
        {
            currentTheme="dark";
        }
        //now update in locak storage

         setTheme(currentTheme);
         document.querySelector('html').classList.add(currentTheme);

         //change of text of button 
         changeTheme.querySelector('span').textContent = currentTheme == 'light'?'Dark':"Light"});

}

//set theme to local storage
function setTheme(theme)
{
    localStorage.setItem("theme", theme);
}
//get theme form local storage
function getTheme()
{
    
    let theme = localStorage.getItem("theme");
    if(theme)
    {
        return theme;
    }
    else
    return "light";
}

