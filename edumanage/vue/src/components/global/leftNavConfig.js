let navConfig = {
    'Stu1_1' : 'Stu1_1',
    'Stu1_2' : 'Stu1_2',
    'Stu2' : 'Stu2',
    'Stu3' : 'Stu3',
    'Stu4_1' : 'Stu4_1',
    'Stu4_2' : 'Stu4_2',
    'Stu4_3' : 'Stu4_3',
};
    
function getActiveNav(href) {
    let key = href.split('/')[1];
    return navConfig[key];
};
    
export default getActiveNav;