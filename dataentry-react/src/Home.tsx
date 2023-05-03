import Buttons from "./components/Buttons";

const Home = () => {
  return (
    <>
      <div><p className="display-5 align-middle">Welcome to Data Entry Application</p></div>
      <div><p className="display-6 align-middle">Technology Used: <span>Java Servlet, ReactJS, postgresql database</span></p></div>
      <Buttons />
    </>
  );
};

export default Home;
