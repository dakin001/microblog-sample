import React, { useEffect, useState } from 'react';
import { FeedQueryDto, PostApi } from "../apis/index";
import MyFollow from '../components/MyFollow';

function PostPage() {
  const [formData, setFormData] = useState({
  });

  const handleChange = (event) => {
    setFormData(values => ({
      ...formData,
      [event.target.name]: event.target.value
    }))
  }

  const [feed, setFeedData] = useState({
    items: []
  });

  const postApi = new PostApi();

  const handleSubmit = (event) => {
    event.preventDefault(); // Prevent default form submission

    postApi.createPost(formData);
  };

  useEffect(() => {
    let req = new FeedQueryDto();
    let loginUser = JSON.parse(localStorage.getItem("user"));

    let useStream = false;
    if (useStream) {
      postApi.myFeed(req, (error, data, response) => {
        setFeedData({ items: data });
      });
    }
    else {
      postApi.queryUserFeed(loginUser.id, req, (error, data, response) => {
        setFeedData({ items: data });
      });
    }

  }, []);

  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-3">
          <MyFollow />
        </div>
        <div className="col-9">
          <div className="card">
            <div className="card-body">

              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="content"
                    name="content"
                    placeholder="Type something here"
                    value={formData.content}
                    onChange={handleChange}
                    required
                  />
                </div>

                <div className=' '>
                  <button type="submit" className="btn btn-secondary">Post</button>
                </div>
              </form>
            </div>
          </div>
          <div className="mb-4"> </div>

          <ul className="list-group">
            {feed.items.map((item) =>
              <li key={item.id} className="list-group-item d-flex justify-content-between align-items-start">
                <div className="ms-2 me-auto container">
                  <div className="row">
                    <div className="col-2 fw-bold">{item.user.name}</div>

                    <div className="col-10">{item.content}</div>
                  </div></div>

              </li>
            )}
          </ul>
        </div>
      </div>

    </div>
  );
}

export default PostPage;
